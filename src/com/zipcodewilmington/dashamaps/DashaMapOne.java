package com.zipcodewilmington.dashamaps;


public class DashaMapOne implements HashMapX{

    private Node[] hashArray;

    public DashaMapOne(){
        hashArray = new Node[26];
        char a = 'a';
        for (int i = 0; i < 26; i++) {
            char holder = (char) (a + i);
            Character alphabet = holder;
            hashArray[i] = new Node(alphabet.toString(), null, null);
        }
    }

    public Node[] getHashArray() {
        return hashArray;
    }

    private String HashFunctionOne(String input) {
        if (input.length() > 0) {
            return (String.valueOf(input.charAt(0))).toLowerCase();
        }
        return null;
    }

    public void set(String key, Integer value) {
        String keyHash = HashFunctionOne(key);
        Node newNode = new Node(key, value, null);
        append(keyHash, newNode);
    }

    public void append(String letter, Node node){
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getKey().equals(letter)){
                Node tempNode = hashArray[i];
                while (tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                }
                tempNode.setNext(node);
            }
        }
    }

    public void delete(String key) {
        String keyHash = HashFunctionOne(key);
        removeIn(keyHash, key);
    }

    public void removeIn(String keyHash, String key){
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getKey().equals(keyHash)){
                Node tempNode = hashArray[i];
                Node toReconnect = hashArray[i];
                while (tempNode.getNext() != null && !tempNode.getKey().equals(key)){
                    toReconnect = tempNode;
                    tempNode = tempNode.getNext();
                }
                toReconnect.setNext(tempNode.getNext());
                tempNode.setNext(null);
            }
        }
    }

    public Integer get(String key) {
        String keyHash = HashFunctionOne(key);
        Node newNode = findIn(keyHash, key);
        return newNode.getValue();
    }

    public Node findIn(String keyHash, String key){
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getKey().equals(keyHash)){
                Node tempNode = hashArray[i];
                while (!tempNode.getKey().equals(key)){
                    tempNode = tempNode.getNext();
                }
                return tempNode;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getNext() != null){
                return false;
            }
        }
        return true;
    }

    public long size() {
        long count = 0;
        char a = 'a';
        for (int i = 0; i < hashArray.length; i++) {
            char holder = (char) (a + i);
            Character alphabet = holder;
            count += bucketSize(alphabet.toString());
        }
        return count;
    }

    public int bucketSize(String keyHash) {
        int count = 0;
        keyHash = HashFunctionOne(keyHash);
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getKey().equals(keyHash)){
                Node tempNode = hashArray[i];
                while (tempNode.getNext() != null){
                    tempNode = tempNode.getNext();
                    count++;
                }
            }
        }
        return count;
    }
}