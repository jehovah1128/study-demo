package com.study.cache;

import com.study.common.util.HttpClientUtil;

public class HystrixQueueTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new TestThread(i).start();
        }
    }



    private static class  TestThread extends  Thread{
        private int index;

        public TestThread(int index){
            this.index = index;
        }

        @Override
        public void run() {
            String result = HttpClientUtil.doGet("http://192.168.1.15:9998/cache/product?productId=472445665834831878");
            System.out.println("第" + (index + 1) + "次请求,结果:" + result);
        }
    }

}
