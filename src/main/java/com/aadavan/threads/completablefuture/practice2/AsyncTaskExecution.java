package com.aadavan.threads.completablefuture.practice2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AsyncTaskExecution {
    public static void main(String[] args) {
        Supplier<List<Long>> supplyIds = () -> {
            sleep(200);
            System.out.println(threadName() + "\tSending list of ids");
            return List.of(1l, 2l, 3l, 4l, 5l);
        };

        Function<List<Long>, List<User>> fetchUsers = ids -> {
            sleep(300);
            System.out.println(threadName() + "\tSending users based on the ids");
            return ids.stream().map(User::new).collect(Collectors.toList());
        };

        Consumer<List<User>> users = u -> {
            System.out.println(threadName() + "\tPrinting all the users");
            u.stream().forEach(System.out::println);
        };

        CompletableFuture<List<Long>> cf = CompletableFuture.supplyAsync(supplyIds);
        cf.thenApply(fetchUsers)
                .thenAccept(users);

        sleep(1_000);
    }

    private static String threadName() {
        return Thread.currentThread().getName();
    }

    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class User {
        private Long id;

        public User(Long id) {
            this.id = id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("id=").append(id);
            sb.append('}');
            return sb.toString();
        }
    }
}
