import java.io.File;

public class FileDeletion {
    public static void deleteFilesInDirectory(String directoryPath) {
        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Get all files in the directory
        File[] files = directory.listFiles();

        if(files != null) { // this line is needed for null pointer exception
            for (File file : files) {
                if (!file.delete()) { // attempt to delete each file
                    System.out.println("Failed to delete " + file);
                }
            }
        } else {
            System.out.println("Directory does not exist or is not a directory: " + directoryPath);
        }
    }
}
import java.io.File;

public class DirectoryDeletion {
    public static void deleteDirectory(String directoryPath) {
        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Delete the directory recursively
        deleteDirectoryRecursively(directory);
    }

    private static void deleteDirectoryRecursively(File file) {
        // If the File object represents a directory, delete its children first
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteDirectoryRecursively(child);
            }
        }

        // Delete the file or directory (in case of directory, it should be empty by now)
        if (!file.delete()) {
            System.out.println("Failed to delete " + file);
        }
    }
}
public class MyTask {
    public synchronized void deleteDirectory(String directoryPath) {
        // deletion logic here
    }

    public synchronized void abcd() {
        // other logic here
    }
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTask {
    private final Lock lock = new ReentrantLock();

    public void deleteDirectory(String directoryPath) {
        lock.lock();
        try {
            // deletion logic here
        } finally {
            lock.unlock();
        }
    }

    public void abcd() {
        lock.lock();
        try {
            // other logic here
        } finally {
            lock.unlock();
        }
    }
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTask {
    private final Lock lock = new ReentrantLock();

    public void deleteDirectory(String directoryPath) {
        lock.lock();
        try {
            // deletion logic here
        } catch (Exception e) {
            // handle exception
            System.out.println("Exception occurred in deleteDirectory: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void abcd() {
        lock.lock();
        try {
            // other logic here
        } catch (Exception e) {
            // handle exception
            System.out.println("Exception occurred in abcd: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class LockService {
    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.locks.Lock;

public class MyTask1 {
    private final Lock lock;

    @Autowired
    public MyTask1(LockService lockService) {
        this.lock = lockService.getLock();
    }

    public void deleteDirectory(String directoryPath) {
        lock.lock();
        try {
            // deletion logic here
        } catch (Exception e) {
            // handle exception
            System.out.println("Exception occurred in deleteDirectory: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.locks.Lock;

public class MyTask2 {
    private final Lock lock;

    @Autowired
    public MyTask2(LockService lockService) {
        this.lock = lockService.getLock();
    }

    public void abcd() {
        lock.lock();
        try {
            // other logic here
        } catch (Exception e) {
            // handle exception
            System.out.println("Exception occurred in abcd: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUtils {
    private static volatile ReentrantLockUtils instance;
    private final Lock lock;

    private ReentrantLockUtils() {
        lock = new ReentrantLock();
    }

    public static ReentrantLockUtils getInstance() {
        if (instance == null) {
            synchronized (ReentrantLockUtils.class) {
                if (instance == null) {
                    instance = new ReentrantLockUtils();
                }
            }
        }
        return instance;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
ReentrantLockUtils lockUtils = ReentrantLockUtils.getInstance();

// 获取锁
lockUtils.lock();
try {
    // 对共享资源进行操作
    // ...
} finally {
    // 释放锁
    lockUtils.unlock();
}
public class ReentrantLockUtils {
    private ReentrantLockUtils() {}

    private static class SingletonHolder {
        private static final ReentrantLockUtils INSTANCE = new ReentrantLockUtils();
    }

    public static ReentrantLockUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // ...
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUtils {
    private static volatile ReentrantLockUtils instance;
    private final Lock lock;

    private ReentrantLockUtils() {
        lock = new ReentrantLock();
    }

    public static ReentrantLockUtils getInstance() {
        if (instance == null) {
            synchronized (ReentrantLockUtils.class) {
                if (instance == null) {
                    instance = new ReentrantLockUtils();
                }
            }
        }
        return instance;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
ReentrantLockUtils lockUtils = ReentrantLockUtils.getInstance();

// 获取锁
lockUtils.lock();
try {
    // 对共享资源进行操作
    // ...
} finally {
    // 释放锁
    lockUtils.unlock();
}
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class LockConfiguration implements InitializingBean {
    private Lock lock;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 在这里初始化锁对象
        lock = new ReentrantLock();
    }

    @Bean
    public Lock lock() {
        return lock;
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;

@Component
public class MyTask {
    private final Lock lock;

    @Autowired
    public MyTask(Lock lock) {
        this.lock = lock;
    }

    public void doSomething() {
        lock.lock();
        try {
            // 执行需要加锁的操作
            // ...
        } finally {
            lock.unlock();
        }
    }
}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class LockConfiguration {

    @Bean
    @Scope("singleton")
    public Lock lock() {
        return new ReentrantLock();
    }
}
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

ExecutorService executor = Executors.newFixedThreadPool(nThreads);
