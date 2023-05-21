package com.sangeng;


import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        test25();
        test24();
//        test23();
//        testNegate();
//        testOr();
//testAnd();
//        test22();
//        test21();

//        test20();
//        test19();
//        test18();
//        test17();
//        test16();
//        test15();
//        test14();
//        test13();
//        test12();
//        test11();
//        test10();

//        test9();

//        test8();
//        test7();

//        test6();

//        test5();
//        test4();
//        test3();
//        test2();
//        test1();
//        test();

        //01 打印所有年龄小于18的作家的名字，并且要注意去重
//        List<Author> authors = getAuthors();

        //filter
//        authors.stream()
//                .distinct()
//                .filter(author -> author.getAge()>18)
//                .forEach(author -> System.out.println(author.getName()));
        // 02创建流
        /*// 单列集合
        Stream<Author> stream = authors.stream();
        //数组
        Integer[] arr= {1,2,3,4,5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        //双列集合
        Map<String ,Integer> map = new HashMap<>();
        map.put("那笔小新",19);
        map.put("黑子",17);
        map.put("日像香",16);
        Stream<Map.Entry<String, Integer>> stream2 = map.entrySet().stream();*/


    }

    private static void test25() {
        List<Author> authors = getAuthors();
        authors.stream().parallel()
                .map(author -> author.getAge())
                .filter(age->age>10)
                .forEach(age-> System.out.println(age));
    }

    private static void test24() {
        List<Author> authors = getAuthors();
        authors.stream()
                .mapToInt(author->author.getAge())
                .map(age->age+10)
                .filter(age->age>18)
                .forEach(age-> System.out.println(age));
    }

    private static void test23() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getAge)
                .map(String::valueOf)
                .forEach(System.out::println);
    }

    private static void testNegate() {
        //打印作家中年龄不大于17的作家。
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17;
                    }
                }.negate())
                .forEach(author -> System.out.println(author));
    }

    private static void testOr() {
        List<Author> authors = getAuthors();

        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17;
                    }
                }.or(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length()<2;
                    }
                }))
                .forEach(author -> System.out.println(author));
    }

    private static void testAnd() {
        //打印作家中年龄大于17并且姓名的长度大于1的作家。
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17)
                        .and(author -> author.getName().length()>1))
                .forEach(author -> System.out.println(author));
    }

    private static void test22() {
        //使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(reduce);
    }

    private static void test21() {
        //使用reduce求所有作者中年龄的最大值
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(author -> author.getAge())

                .reduce(Integer.MIN_VALUE, (integer, integer2) -> integer < integer2 ? integer2 : integer);
        System.out.println(reduce);

    }

    private static void test20() {
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> {
                    return result + element;
                });
        System.out.println(reduce);
    }

    private static void test19() {
        //	获取一个年龄最小的作家，并输出他的姓名。
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
//                .distinct()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
//        System.out.println(first);
        first.ifPresent(author -> System.out.println(author.getName()));

        /*List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();

        first.ifPresent(author -> System.out.println(author.getName()));*/
    }

    private static void test18() {
        //获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getName()));
    }

    private static void test17() {
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(b);
    }

    private static void test16() {
        //判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(b);
    }

    private static void test15() {
        //获取一个Map集合，map的key为作者名，value为List<Book>
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(collect);
    }

    private static void test14() {
        //获取一个所有书名的Set集合。
        List<Author> authors = getAuthors();
        Set<String> collect = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getName())
                .distinct()
                .collect(Collectors.toSet());
        System.out.println(collect);
    }

    private static void test13() {
        // 获取一个存放所有作者名字的List集合
        List<Author> authors = getAuthors();
        List<String> collect = authors.stream()
                .distinct()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void test12() {
        List<Author> authors = getAuthors();
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getScore())
                .min((o1, o2) -> o1 - o2);
        System.out.println(min.get());

    }

    private static void test11() {
        //分别获取这些作家的所出书籍的最高分和最低分并打印。
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);
        System.out.println(max.get());
    }

    private static void test10() {
        //打印这些作家的所出书籍的数目，注意删除重复元素。
        List<Author> authors = getAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(bookStream -> bookStream.getName())
                .distinct()

                .count();

//        long count = authors.stream()
//                .flatMap(author -> author.getBooks().stream())
//                .distinct()
//                .count();
        System.out.println(count);

    }

    private static void test9() {
        //	打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getAge()));
    }

    private static void test8() {
        //打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static void test7() {
        //打印所有书籍的名字。要求对重复的元素进行去重。
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    private static void test6() {
        //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge()-o1.getAge();
                    }
                })
                .limit(2)
                .forEach(author -> System.out.println(author.getAge()));
    }

    private static void test5() {
        //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
                }

    private static void test4() {
        //打印所有作家的姓名，并且要求其中不能有重复元素。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));

    }

    private static void test3() {
        //每个作家年龄➕10
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getAge()+10)
                .forEach(age-> System.out.println(age));
    }

    private static void test2() {
        //打印所有作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .forEach(s -> System.out.println(s));
    }

    private static void test1() {
        //打印所有姓名长度大于1的作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test() {
        //打印所有作家的姓名
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));

    }


    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
}
