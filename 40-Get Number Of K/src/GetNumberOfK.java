/**
 * @Author: cuizhe
 * @Date: 2019/3/18 19:45
 */
public class GetNumberOfK {
    /**
     * 题目：
     * 统计一个数字在排序数组中出现的次数。
     */

    /**
     * 思路：
     * 通过二分查找，先找到 k 的值，然后向两遍搜索，找到第一个值和最后一个值。
     *
     * @param array 排序数组
     * @param k 待查找的数
     * @return k 在数组中出现的次数
     */
    public static int getNumberOfK_1(int [] array , int k) {
        if (array==null || array.length<=0){
            return 0;
        }
        int KIndex = binarySearch(array,k);
        if (KIndex<0){
            return 0;
        }
        int firstKIndex = KIndex;
        int endKIndex = KIndex;
        while (array[firstKIndex]==k){
            int index = firstKIndex - 1;
            if (index>=0){
                if (array[index]!=k){
                    break;
                }else {
                    firstKIndex--;
                }
            }else {
                break;
            }
        }
        while (array[endKIndex]==k){
            int index = endKIndex + 1;
            if (index<array.length){
                if (array[index]!=k){
                    break;
                }else {
                    endKIndex++;
                }
            }else {
                break;
            }
        }
        System.out.println("kindex:"+KIndex+",firstKIndex:"+firstKIndex+",endKIndex:"+endKIndex);
        return endKIndex-firstKIndex+1;
    }

    private static int binarySearch(int[] arr,int k){
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = arr[mid];

            if (midVal < k)
                low = mid + 1;
            else if (midVal > k)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }

    /**
     * 思路：
     * 遍历整个数组，简单暴力的解法，O(n)
     *
     * @param array 排序数组
     * @param k 待查找的数
     * @return k 在数组中出现的次数
     */
    public static int getNumberOfK_2(int [] array , int k) {
        if (array==null || array.length<=0){
            return 0;
        }
        int count = 0;
        for (int i :
                array) {
            if (i == k){
                count++;
            }
        }
        return count;
    }

    /**
     * 思路：
     * 通过二分查找，找到第一个 k 和最后一个 k
     *
     * @param array 排序数组
     * @param k 待查找的数
     * @return k 在数组中出现的次数
     */
    public static int getNumberOfK_3(int [] array , int k) {
        if (array==null || array.length<=0){
            return 0;
        }
        int first = getFirstK(array,k,0,array.length);
        int end = getEndK(array,k,0,array.length);
        if (first!=-1 && end!=-1){
            return end - first + 1;
        }
        return 0;
    }

    //递归写法
    private static int getFirstK(int[] array, int k, int start, int end) {
        if (start > end){
            return -1;
        }
        int mid = (start+end)>>1;
        if (mid==array.length){
            return -1;
        }
        if (array[mid] > k){
            return getFirstK(array,k,start,mid-1);
        }else if (array[mid] < k){
            return getFirstK(array,k,mid+1,end);
        }else if (mid-1 >= 0 && array[mid-1] == k){
            return getFirstK(array,k,start,mid-1);
        }else {
            return mid;
        }
    }

    //非递归写法
    private static int getEndK(int[] array, int k, int start, int end) {
        int length = array.length;
        int mid = (start+end)>>1;
        while (start<=end){
            if (mid==array.length){
                return -1;
            }
            if (array[mid] > k){
                end = mid - 1;
            }else if (array[mid] < k){
                start = mid + 1;
            }else if (mid+1<length && array[mid+1]==k){
                start = mid + 1;
            }else {
                return mid;
            }
            mid = (start+end)>>1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,3,3,3,3,4,5};
        System.out.println(getNumberOfK_3(a,6));
    }

}
