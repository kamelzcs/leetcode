/*
 * lru.cpp
 * Copyright (C) 2015 zhao <zhao@kamel-Desktop>
 *
 * Distributed under terms of the MIT license.
 */

#include<unordered_map>
#include<list>
using namespace std;




class LRUCache{
private:
    struct item{
        int key, val;
        item(int k, int v):key(k), val(v){
        }
    };
    typedef list<item> LITEM;
    typedef unordered_map<int, LITEM::iterator> MITEM;

    LITEM lru_list;
    MITEM lru_key;
    int lru_cap;
public:
    LRUCache(int capacity):lru_cap(capacity) {
    }
    int get(int key) {
        auto itr = lru_key.find(key);
        if(itr == lru_key.end()){
            return -1;
        }
        touch(itr);
        return lru_key[key]->val;
    }
    void set(int key, int value) {
        auto itr = lru_key.find(key);
        if(itr != lru_key.end()){
            touch(itr);
            lru_key[key]->val = value;
        }
        else{
            if(lru_cap == lru_key.size()){
                lru_key.erase(lru_list.back().key);
                lru_list.pop_back();
            }
            lru_list.push_front(item{key, value});
            lru_key[key] = lru_list.begin();
        }
    }
    void touch(MITEM::iterator itr){
        lru_list.erase(itr->second);
        lru_list.push_front(*(itr->second));
        itr->second = lru_list.begin();
    }
};
