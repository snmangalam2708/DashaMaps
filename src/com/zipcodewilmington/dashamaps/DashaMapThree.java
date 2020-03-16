package com.zipcodewilmington.dashamaps;

public class DashaMapThree implements HashMapX {

    private Node[] hashArray;

    public DashaMapThree(){
        hashArray = new Node[676];
        char a = 'a';
        char a1 = 'a';
        int index = 0;
        for (int i = 0; i < 26; i++) {
            char holder = (char) (a + i);
            Character alphabet = holder;
            for (int j = 0; j < 26; j++) {
                char holder1 = (char) (a1 + j);
                Character alphabet1 = holder1;
                hashArray[index] = new Node(alphabet.toString() + alphabet1.toString(), null, null);
                index++;
            }
        }
    }

    public Node[] getHashArray(){
        return hashArray;
    }

    private String HashFunctionThree(String input) {
        if (input.length() > 1) {
            return input.substring(0,2).toLowerCase();
        }
        return null;
    }

    public void set(String key, Integer value) {
        String keyHash = HashFunctionThree(key);
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
        String keyHash = HashFunctionThree(key);
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
        String keyHash = HashFunctionThree(key);
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
        char a1 = 'a';
        for (int i = 0; i < 26; i++) {
            char holder = (char) (a + i);
            Character alphabet = holder;
            for (int j = 0; j < 26; j++) {
                char holder1 = (char) (a1 + j);
                Character alphabet1 = holder1;
                count += bucketSize(alphabet.toString() + alphabet1.toString());
            }
        }
        return count;
    }


    public int bucketSize(String keyHash) {
        int count = 0;
        keyHash = HashFunctionThree(keyHash);
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i].getKey().equals(keyHash)) {
                Node tempNode = hashArray[i];
                while (tempNode.getNext() != null) {
                    tempNode = tempNode.getNext();
                    count++;
                }
            }
        }
        return count;
    }
}