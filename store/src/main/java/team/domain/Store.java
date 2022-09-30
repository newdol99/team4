package team.domain;

import team.domain.FlowerSold;
import team.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Store_table")
@Data

public class Store  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long flowerId;
    
    
    
    
    
    private Integer flowerCnt;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private Double flowerPrice;
    
    
    
    
    
    private Boolean isOffline;

    @PostPersist
    public void onPostPersist(){


        FlowerSold flowerSold = new FlowerSold(this);
        flowerSold.publishAfterCommit();

    }

    public static StoreRepository repository(){
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(StoreRepository.class);
        return storeRepository;
    }



    public void wrap(WrapCommand wrapCommand){
        FlowerWrapped flowerWrapped = new FlowerWrapped(this);
        flowerWrapped.publishAfterCommit();

    }

    public static void ifOnlineOrder(PaymentCompleted paymentCompleted){

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        FlowerWrapped flowerWrapped = new FlowerWrapped(store);
        flowerWrapped.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCompleted.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            FlowerWrapped flowerWrapped = new FlowerWrapped(store);
            flowerWrapped.publishAfterCommit();

         });
        */

        
    }
    public static void ifOfflineOrder(PaymentCompleted paymentCompleted){

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        FlowerSold flowerSold = new FlowerSold(store);
        flowerSold.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCompleted.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            FlowerSold flowerSold = new FlowerSold(store);
            flowerSold.publishAfterCommit();

         });
        */

        
    }


}
