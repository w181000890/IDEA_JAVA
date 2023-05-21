package com.sangeng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {



//        tetsMap();

//        testIsPresent();

//        testFilter();
        /*Author author = getAuthor();

        Optional<Author> author1 = Optional.ofNullable(author);
        author1.ifPresent(author2 -> System.out.println(author2.getName()));*/

//        Optional<Author> authorOptioanal = getAuthorOptioanal();
//        try{
//            Author author = authorOptioanal.orElseThrow(() -> new RuntimeException("数据为NULL"));
//            System.out.println(author);
//
//        }catch (Throwable throwable){
//            throwable.printStackTrace();
//        }

    }

    private static void tetsMap() {
        Optional<Author> authorOptioanal = getAuthorOptioanal();
        Optional<List<Book>> booksOptional = authorOptioanal.map(author -> author.getBooks());
        booksOptional.ifPresent(books-> System.out.println(books));
    }

    private static void testIsPresent() {
        Optional<Author> authorOptioanal = getAuthorOptioanal();
        if(authorOptioanal.isPresent()){
            System.out.println(authorOptioanal.get().getName());
        }
    }

    private static void testFilter() {
        Optional<Author> authorOptioanal = getAuthorOptioanal();
        authorOptioanal.filter(author -> author.getAge()>18)
                .ifPresent(author -> System.out.println(author.getName()));
    }


    private static Optional<Author> getAuthorOptioanal() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);


        //书籍列表
        List<Book> books1 = new ArrayList<>();


        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        author.setBooks(books1);


        return Optional.ofNullable(author);
    }



    public static Author getAuthor(){
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        return null;
    }


}
