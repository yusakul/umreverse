package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.*;

public enum Gender implements n
{
    MALE(0), 
    FEMALE(1), 
    UNKNOWN(2);
    
    private final int value;
    
    private Gender(final int value) {
        this.value = value;
    }
    
    @Override
    public int getValue() {
        return this.value;
    }
    
    public static Gender findByValue(final int n) {
        switch (n) {
            case 0: {
                return Gender.MALE;
            }
            case 1: {
                return Gender.FEMALE;
            }
            case 2: {
                return Gender.UNKNOWN;
            }
            default: {
                return null;
            }
        }
    }
}
