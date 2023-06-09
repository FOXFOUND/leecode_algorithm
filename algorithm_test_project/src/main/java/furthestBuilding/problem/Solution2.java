package furthestBuilding.problem;

import java.util.PriorityQueue;

class Solution2 {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            int cha = heights[i] - heights[i - 1];
            if (cha > 0) {
                minheap.offer(cha);
                if (minheap.size() > ladders) {
                    bricks -= minheap.poll();
                }
                if (bricks < 0) {
                    return i - 1;
                }
            }
        }
        return n - 1;

    }
}

