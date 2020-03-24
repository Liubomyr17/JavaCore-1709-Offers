package com.company;

/*

1709 offers
Without using synchronized, make the number of proposals made and accepted the same.

Requirements:
1. The Solution class must contain a MakeProposal thread.
2. The Solution class must contain the AcceptProposal thread.
3. The Solution class must contain the public static variable int proposal.
4. The program should not contain synchronized methods or synchronized blocks.
5. The variable int proposal should not be in the local cache.


 */


public class Solution {
    public volatile static int proposal = 0;

    public static void main(String[] args) {
        new AcceptProposal().start();
        new MakeProposal().start();
    }

    public static class MakeProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while (proposal < 10) {
                System.out.println("Сделано предложение №" + (thisProposal + 1));
                proposal = ++thisProposal;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while (thisProposal < 10) {
                if (thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }

            }
        }
    }
}




