package com.aadavan.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SequentialCheck {
    public static void main(String[] args) {
        for (int i=1; i<11; i++) {
            RuleExecutionParams params = new RuleExecutionParams("vreq-" + i, new HashMap());
            if (i == 7 || i == 5) {
                RuleExecutor p = new RuleExecutor(i, true, i * 2, params);
                Thread t = new Thread(p);
                t.start();
            } else {
                RuleExecutor p = new RuleExecutor(i, false, 0, params);
                p.run();
            }
        }
    }

    static class RuleExecutor implements Runnable {
        private int i;
        private RuleExecutionParams params;
        private boolean wait;
        private long waitTime;

        RuleExecutor(int i, boolean wait, long waitTime, RuleExecutionParams params) {
            this.i = i;
            this.wait = wait;
            this.waitTime = waitTime;
            this.params = params;
        }

        @Override
        public void run() {
            if (wait) {
                try {
                    System.out.println("Waiting for " + i + " SECONDS.");
                    TimeUnit.SECONDS.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Executing Rule, Number is " + i); // use that params here
            System.out.println("for Rule number " + i + " the params = " + params);
        }
    }

    static class RuleExecutionParams {
        String vreq;
        Map vreqMap;

        public RuleExecutionParams(String vreq, Map vreqMap) {
            this.vreq = vreq;
            this.vreqMap = vreqMap;
        }

        public String getVreq() {
            return vreq;
        }

        public Map getVreqMap() {
            return vreqMap;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("RuleExecutionParams{");
            sb.append("vreq='").append(vreq).append('\'');
            sb.append(", vreqMap=").append(vreqMap);
            sb.append('}');
            return sb.toString();
        }
    }
}
