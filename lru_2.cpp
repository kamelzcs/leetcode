#include <map>
#include <vector>
#include <list>
using namespace std;

class item{
    public:
        int key, value;
        item(int k, int v): key(k), value(v){
        }
};
class LRUCache{
public:
    list<item> lst;
    map<int, list<item>::iterator> kv;
    int cap;
    LRUCache(int capacity) {
        cap = capacity;
        lst.clear();
        kv.clear();
    }
    
    int get(int key) {
        auto it = kv.find(key);
        if(it == kv.end()){
            return -1;
        }
        touch(key);
        return it->second->value;
    }
    
    void set(int key, int value) {
        auto it = kv.find(key);
        if(it != kv.end()){
            touch(key);
            kv[key]->value = value;
        }
        else{
            if(cap == 0){
                kv.erase(lst.back().key);
                lst.pop_back();
            }
            lst.push_front(item(key, value));
            kv[key] = lst.begin();
            if(cap){
                cap--;
            }
        }
    }

    void touch(int key){
        list<item>::iterator it = kv[key];
        lst.erase(it);
        lst.push_front(*it);
        kv[key] = lst.begin();
    }
};
