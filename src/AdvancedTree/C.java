package AdvancedTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class C {
    public static ArrayList<Long> nodeList;
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long T = input.nextLong();
        for (long t = 0; t < T; t++) {
            long nodeCounts = input.nextLong();
            nodeList = new ArrayList<>();
            long temp1 = -1;
            nodeList.add(temp1);
            
            for (long i = 0; i < nodeCounts; i++) {
                insert(input.nextLong());
            }
            
            if (nodeCounts == 1) {
                System.out.println(nodeList.get(1));
                return;
            }
            
            long result = 0;
            long temp;
            while (true) {
                if (nodeList.size() - 1 < 2) {
                    break;
                }
                temp = nodeList.get(1);
                deleteMin();
                temp += nodeList.get(1);
                result += temp;
                deleteMin();
                insert(temp);
            }
            
            System.out.println(result);
        }
    }
    
    public static void insert(long insert) {
        if (nodeList.size() == 1) {
            nodeList.add(insert);
            return;
        }
        int index = (nodeList.size()) / 2;
        int indexInsert = nodeList.size();
        nodeList.add(insert);
        if (nodeList.get(index) > insert) {
            Collections.swap(nodeList, index, indexInsert);
            while (true) {
                indexInsert = index;
                index /= 2;
                if (index == 0) {
                    break;
                } else if (nodeList.get(index) > insert) {
                    Collections.swap(nodeList, index, indexInsert);
                } else {
                    break;
                }
            }
        }
    }
    
    public static void deleteMin() {
        if (nodeList.size() == 1) {
            return;
        } else if (nodeList.size() == 2) {
            nodeList.remove(nodeList.size() - 1);
            return;
        } else {
            Collections.swap(nodeList, 1, nodeList.size() - 1);
            nodeList.remove(nodeList.size() - 1);
        }
        int index = 1;
        int indexCompare = index;
        while (true) {
            index = indexCompare;
            indexCompare = index * 2;
            if (indexCompare > nodeList.size() - 1) {
                break;
            } else {
                if (indexCompare + 1 > nodeList.size() - 1) {
                    if (nodeList.get(index) > nodeList.get(indexCompare)) {
                        Collections.swap(nodeList, index, indexCompare);
                    } else {
                        break;
                    }
                } else {
                    if (nodeList.get(indexCompare) > nodeList.get(indexCompare + 1)) {
                        indexCompare++;
                    }
                    
                    if (nodeList.get(index) > nodeList.get(indexCompare)) {
                        Collections.swap(nodeList, index, indexCompare);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}