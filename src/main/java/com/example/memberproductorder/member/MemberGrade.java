package com.example.memberproductorder.member;

public enum MemberGrade {
    BASIC {
        @Override
        public int applyDiscount(int price) {
            return price;
        }
    },
    VIP {
        @Override
        public int applyDiscount(int price) {
            return Math.max(price-1000, 0);
        }
    };

    public abstract int applyDiscount(int price);
}
