package pt.demos.news.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import pt.demos.news.models.News;

@Service
@EnableAsync
public class NewsService implements INewsService {

    private static final String RESET = "\033[0m";  // Text Reset
    private static final String GREEN = "\033[0;32m";   // GREEN
    private static final String YELLOW = "\033[0;33m";  // YELLOW
    private static final String RED = "\033[0;31m";     // RED
    
    private static final String NEWS_RECEIVED = "news received";
    private static final String NEWS_LOST = "news lost, please retry";
    
    private static final String FAIL_BROADCAST = "FAIL_BROADCAST";
    private static final String FAIL_SAVE = "FAIL_SAVE";

    @Override
    public ResponseEntity<String> handleNews(News news) {

        var saved = saveNews(news);
        
        if(saved.join()){
            broadcastNews(news);
            return ResponseEntity.status(HttpStatus.OK).body(NEWS_RECEIVED);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(NEWS_LOST);
        }
    }

    @Async("asyncExecutor")
    private CompletableFuture<Void> broadcastNews(News news) {

        System.out.println(YELLOW +  "Broadcasting news..." + RESET);
        
        try {
            //Fake broadcasting news to downstream channels
            TimeUnit.SECONDS.sleep(10);

            if(news.getTitle().equals(FAIL_BROADCAST)){
                throw new InterruptedException("Failed to broadcast news to downstream channels!");
            }

            System.out.println(YELLOW + "News broadcasted successfully!"+ RESET);

        } catch (InterruptedException e) {    
            System.out.println(RED + "News could not be broadcasted!"+ RESET);
        }

        return CompletableFuture.completedFuture(null);
    }

    @Async("asyncExecutor")
    private CompletableFuture<Boolean> saveNews(News news) {

        System.out.println(GREEN + "Saving news..."+ RESET);
        
        try {
            //Fake save news into DB
            TimeUnit.SECONDS.sleep(1);

            if(news.getTitle().equals(FAIL_SAVE)){
                throw new InterruptedException("Failed to save news in DB!");
            }

            System.out.println(GREEN + "News saved succesfully!"+ RESET);
            return CompletableFuture.completedFuture(true);

        } catch (InterruptedException e) {    
            System.out.println(RED + "News could not be saved!"+ RESET);
            return CompletableFuture.completedFuture(false);
        }
    }
    
}
