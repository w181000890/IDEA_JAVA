package com.sangeng;

import lombok.val;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        test12();
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

    private static void test12() {

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
