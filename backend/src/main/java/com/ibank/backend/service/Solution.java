package com.ibank.backend.service;

 public class Solution {


    public static int solution(int[] blocks){
        int n = blocks.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // left[i] of left array stores about how  many elements left to ith block whoose height is greater
        left[0] = 0;
        for(int i = 1; i < n; i++){
        if(blocks[i] <= blocks[i - 1])
        left[i] = left[i - 1] + 1;
        else
        left[i] = 0;
        }
        // right[i] of right array stores about how  many elements right to ith block whoose height is greater
        right[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--){
        if(blocks[i] <= blocks[i + 1])
        right[i] = right[i + 1] + 1;
        else
        right[i] = 0;
        }
        
        // placing boths frog at all blocks one by one and picking the place which can make both forgs
        int Max = 0;
        for(int i = 0; i < n; i++){
        int x = left[i] + right[i] + 1;
        if(x > Max)
        Max = x;
        } 
        return Max;
    
    }

    
}