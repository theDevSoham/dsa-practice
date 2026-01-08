import java.util.HashMap;
import java.util.Scanner;

class PatternSolution {
    private static HashMap<String, Patterns> registry = new HashMap<String, Patterns>();

    static {
        registry.put("1", new Pattern1());
        registry.put("2", new Pattern2());
        registry.put("3", new Pattern3());
        registry.put("4", new Pattern4());
        registry.put("5", new Pattern5());
        registry.put("6", new Pattern6());
        registry.put("7", new Pattern7());
        registry.put("8", new Pattern8());
        registry.put("9", new Pattern9());
        registry.put("10", new Pattern10());
        registry.put("11", new Pattern11());
        registry.put("12", new Pattern12());
        registry.put("13", new Pattern13());
        registry.put("14", new Pattern14());
        registry.put("15", new Pattern15());
        registry.put("16", new Pattern16());
        registry.put("17", new Pattern17());
        registry.put("18", new Pattern18());
        registry.put("19", new Pattern19());
        registry.put("20", new Pattern20());
        registry.put("21", new Pattern21());
        registry.put("22", new Pattern22());
    }

    public static void main(String[] args) {
        System.out.println("Running: " + PatternSolution.class.getName());
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the pattern number: ");
        String key = scanner.next();

        System.out.print("Enter number of lines: ");
        int rows = scanner.nextInt();

        scanner.close();

        try {
            Patterns p = registry.get(key);
            p.drawPattern(rows);
        } catch (Exception e) {
            System.out.println("The system couldn't find the specified pattern");
            System.out.print(e);
        }
    }
}

interface Patterns {
    void drawPattern(int n);
}

class Pattern1 implements Patterns {
    /**
     * ****
     * ****
     * ****
     * ****
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

class Pattern2 implements Patterns {
    /**
     * *
     * **
     * ***
     * ****
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

class Pattern3 implements Patterns {
    /**
     * 1
     * 12
     * 123
     * 1234
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }
}

class Pattern4 implements Patterns {
    /**
     * 1
     * 22
     * 333
     * 4444
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(i + 1);
            }
            System.out.println();
        }
    }
}

class Pattern5 implements Patterns {
    /**
     * *****
     * ****
     * ***
     * **
     * *
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - 1 - i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

class Pattern6 implements Patterns {
    /**
     * 12345
     * 1234
     * 123
     * 12
     * 1
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - 1 - i; j++) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }
}

class Pattern7 implements Patterns {
    /**
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= 2 * i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

class Pattern8 implements Patterns {
    /**
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    public void drawPattern(int n) {
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < (2 * n - 1) - 2 * i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

class Pattern9 implements Patterns {
    /**
     *     *    
     *    ***
     *   *****
     *  *******
     * *********
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     */
    Patterns top = new Pattern7();
    Patterns bottom = new Pattern8();
    public void drawPattern(int n) {
        top.drawPattern(n);
        bottom.drawPattern(n);
    }
}

class Pattern10 implements Patterns {
    /**
     * *
     * **
     * ***
     * ****
     * *****
     * ****
     * ***
     * **
     * *
     */
    public void drawPattern(int n) {
        for (int i = 0; i < 2 * n - 1; i++) {
            int numberOfStars = i + 1;
            if (i > n - 1) {
                numberOfStars = 2 * n - (i + 1);
            }

            for (int j = 0; j < numberOfStars; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

class Pattern11 implements Patterns {
    /**
     * 1
     * 0 1
     * 1 0 1
     * 0 1 0 1
     * 1 0 1 0 1
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            int cursor = 1;

            if (i % 2 == 0) cursor = 0;

            for (int j = 0; j <= i; j++) {
                System.out.print(cursor + " ");
                cursor = -1 * (cursor - 1);
            }

            System.out.println();
        }
    }
}

class Pattern12 implements Patterns {
    /**
     * 1      1
     * 12    21
     * 123  321
     * 12344321
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + 1);
            }

            for (int j = 0; j <= 2 * ((n - 1) - i) - 1; j++) {
                System.out.print(" ");
            }

            for (int j = i; j >= 0; j--) {
                System.out.print(j + 1);
            }

            System.out.println();
        }
    }
}

class Pattern13 implements Patterns {
    /**
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     * 11 12 13 14 15
     */
    public void drawPattern(int n) {
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(count + " ");
                count++;
            }
            System.out.println();
        }
    }
}

class Pattern14 implements Patterns {
    /**
     * A
     * AB
     * ABC
     * ABCD
     * ABCDE
     */
    public void drawPattern(int n) {
        int a = 65;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char)(a + j));
            }
            System.out.println();
        }
    }
}

class Pattern15 implements Patterns {
    /**
     * ABCDE
     * ABCD
     * ABC
     * AB
     * A
     */
    public void drawPattern(int n) {
        int a = 65;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print((char)(a + j));
            }
            System.out.println();
        }
    }
}

class Pattern16 implements Patterns {
    /**
     * A
     * BB
     * CCC
     * DDDD
     */
    int a = 65;
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char)(a + i));
            }
            System.out.println();
        }
    }
}

class Pattern17 implements Patterns {
    /**
     *    A
     *   ABA
     *  ABCBA
     * ABCDCBA
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            int a = 65;
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2 * i) + 1; j++) {
                if (j <= i) {
                    System.out.print((char) a++);
                } else {
                    System.out.print((char) ((--a) - 1));
                }
            }
            System.out.println();
        }
    }
}

class Pattern18 implements Patterns {
    /**
     * E
     * D E
     * C D E
     * B C D E
     * A B C D E
     */
    public void drawPattern(int n) {
        int a = 65;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (a + (n - 1 - i) + j) + " ");
            }
            System.out.println();
        }
    }
}

class Pattern19 implements Patterns {
    /**
     * **********
     * ****  ****
     * ***    ***
     * **      **
     * *        *
     * *        *
     * **      **
     * ***    ***
     * ****  ****
     * **********
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * (n - 1 - i); j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

class Pattern20 implements Patterns {
    /**
     * *        *
     * **      **
     * ***    ***
     * ****  ****
     * **********
     * ****  ****
     * ***    ***
     * **      **
     * *        *
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * (n - 1 - i); j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j <= (2 * i) + 1; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < n - 1 - i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

class Pattern21 implements Patterns {
    /**
     * ****
     * *  *
     * *  *
     * ****
     */
    public void drawPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}

class Pattern22 implements Patterns {
    /**
     * 4 4 4 4 4 4 4
     * 4 3 3 3 3 3 4
     * 4 3 2 2 2 3 4
     * 4 3 2 1 2 3 4
     * 4 3 2 2 2 3 4
     * 4 3 3 3 3 3 4
     * 4 4 4 4 4 4 4 
     */ 
    public void drawPattern(int n) {
        int size = 2 * n - 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int top = i;
                int left = j;
                int bottom = size - 1 - i;
                int right = size - 1 - j;

                int minDistance = Math.min(
                        Math.min(top, bottom),
                        Math.min(left, right)
                );

                System.out.print((n - minDistance) + " ");
            }
            System.out.println();
        }
    }
}