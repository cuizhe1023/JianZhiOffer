/**
 * @Author: cuizhe
 * @Date: 2019/3/28 20:37
 */
public class Match {
    /**
     * 题目：
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。
     * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */

    /**
     * 思路：
     * 首先，我们考虑有以下情况：
     * 1.两个字符串都为空，返回 true
     * 2.当 str 不为空，pattern 为空时，返回 false
     * (因为这样就无法匹配到字符了，但是当字符串为空，模式不为空时，就有可能匹配成功，
     * 比如说第二个字符串为 "a*a*a*"，由于 * 之前的元素可以出现 0 次，所以可能匹配成功。)
     * 接着，我们就开始匹配字符串了：
     * 开始匹配第一个字符，这里有两种情况，匹配成功，匹配不成功
     * 但是考虑到 pattern 下一个字符可能是 '*'，我们这里分两种情况讨论。
     * pattern 下一个字符是 '*' 和 pattern 下一个字符不是 '*'
     *
     * 1.pattern 下一个字符不是 '*'
     * 这种情况比较简单，直接匹配当前字符就可以了。
     * 如果匹配成功，那么字符串和模式都后移一个字符，然后匹配剩余的；
     * 如果匹配失败，直接返回 false。
     * 这里所说的“匹配成功”，除了两个字符串相同外，还有一种情况，就是 pattern 的当前字符为 '.'，同时注意越界。
     *
     * 2.pattern 下一个字符是 '*'
     * 这种情况稍微复杂一点了，因为 '*' 可以代表 0 个或者多个，需要把这些都考虑到：
     * a.当 '*' 匹配 0 个字符,即 str 的字符跟 pattern 的字符不匹配时，
     * str 当前字符不变，pattern 当前字符后移两位，跳过这个 '*' 符号；
     * b.当 '*' 匹配 1 个或多个，即 str 的字符和 pattern 的字符匹配时，有 3 种匹配方式：
     * 1>.str 后移 1 字符，pattern 不变，即继续匹配字符下一位，因为 '*' 可以匹配多位；
     * 2>.pattern 后移2字符，相当于 x* 被忽略，由于 str 移到了下一个字符，而 pattern 字符不变，就回到了上边的情况a；
     *
     * 要时刻注意数组是否越界！
     *
     * @param str 字符串
     * @param pattern 正则表达式的字符串
     * @return 是否匹配
     */
    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null){
            return false;
        }
        return matchCore(str,0,pattern,0);
    }

    private static boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //1.有效性检验，str 到尾，pattern 到尾，匹配成功
        if (str.length == strIndex && pattern.length == patternIndex){
            return true;
        }
        //2.pattern 先到尾，匹配失败
        if (str.length != strIndex && pattern.length == patternIndex){
            return false;
        }
        //3.开始匹配，pattern 的下一个是 '*'，且 str 的字符和 pattern 的字符匹配，分两种匹配模式；
        //如果不匹配，pattern 后移两位。
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*'){
            if (str.length != strIndex && str[strIndex]==pattern[patternIndex]
                    || pattern[patternIndex] == '.' && str.length != strIndex){
                return matchCore(str,strIndex + 1,pattern,patternIndex)
                        || matchCore(str,strIndex,pattern,patternIndex + 2);
            }else {
                return matchCore(str,strIndex,pattern,patternIndex + 2);
            }
        }
        //4.pattern 下一个字符不是 '*',则直接匹配
        if (str.length != strIndex && pattern[patternIndex] == str[strIndex]
                || str.length != strIndex && pattern[patternIndex] == '.'){
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = ".*";
        System.out.println("是否匹配："+match(s1.toCharArray(),s2.toCharArray()));
    }
}
