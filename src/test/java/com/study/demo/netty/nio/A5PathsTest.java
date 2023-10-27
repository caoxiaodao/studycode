package com.study.demo.netty.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author caonan
 * @createtime 2023/6/29 14:13
 * @Description TODO
 * @Version 1.0
 */
public class A5PathsTest {
    /**
     * Path 获取文件路径
     *  获取方式：Paths.get(绝对路径/相对路径)
     *  常用方法：normalize正常化（如果路径中存在./..会被解析成当前路径/上一级路径）
     * Files 文件/文件夹操作
     *    判断是否存在 exists
     *    创建  目录存在会报错
     *          createDirectory(path) 无法创建多级目录
     *          createDirectories(path)
     *    复制 copy 文件存在会报错，覆盖请设置StandardCopyOption.REPLACE_EXISTING
     *    移动 move
     *         1.文件存在不会报错，会直接覆盖原来文件
     *         2.StandardCopyOption.ATOMIC_MOVE保证文件复制原子性,
     *    删除
     *      delete 只能删除空目录，目录还有内容会报错
     *   *** 层级递进删除
     *     ***  Files.walkFileTree
     *      ***
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("file"+ File.separator+"from.txt");
//        Path toPath = Paths.get("file"+ File.separator+"from_move.txt");
//        System.out.println(path.normalize());
//        Files.move(path,toPath, StandardCopyOption.ATOMIC_MOVE);
        Path pathA = Paths.get("file");
        // 文件遍历删除
        Files.walkFileTree(pathA, new SimpleFileVisitor<Path>(){
            //访问某个文件
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                Files.delete(path);
                return super.visitFile(path, basicFileAttributes);
            }
            //访问文件夹结束之后
            @Override
            public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                Files.delete(path);
                return super.postVisitDirectory(path, e);
            }
        });
    }
}
