/*
* This file implements a stack that remembers the maximum values and allows constant time access to them.
* There is an extra stack used to mark the maximum values
*
* */

import java.util.Stack;

public class maxStack extends Stack {
    private Stack<Integer> mainStack;
    private Stack<Integer> subStack;

    public maxStack() {
        mainStack = new Stack();
        subStack = new Stack();
    }

    public void push(int value){

        if(value>=MaxValue()){ //note the sign "=";
            subStack.push(value);
        }
        mainStack.push(value);

    }
    public int popFrom(){
        int value = mainStack.pop();
        if(value==MaxValue()){
            subStack.pop();
        }
        return value;
    }

    public int MaxValue(){
        if(subStack.isEmpty()){
            return Integer.MIN_VALUE;
        }else{
            return subStack.peek(); //return but not remove  the most recently added element
        }

    }

    public static void main(String[] args) {
        maxStack test = new maxStack();

        test.push(9);

        test.push(5);
        test.push(3);
        test.push(7);
        test.push(278);
        test.push(-12);
        test.push(1);
        test.popFrom();
        test.popFrom();
        System.out.println("the biggest is "+test.MaxValue());
    }
}
