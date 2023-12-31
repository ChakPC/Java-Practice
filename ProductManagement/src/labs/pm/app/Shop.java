/*
 * Copyright (C) 2023 purnadip_chakrabarti
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package labs.pm.app;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import static labs.pm.data.Rating.*;

/**
 * (@code Shop) This class handles all operations of a shop
 * @version 1.0
 * @author Purnadip Chakrabarti
 */
public class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ProductManager pm = ProductManager.getInstance();
        AtomicInteger clientCount = new AtomicInteger(0);
        
        Callable<String> client = () -> {
            String clientId = "Client " + clientCount.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            int productId = ThreadLocalRandom.current().nextInt(63) + 101;
            String languageTag = ProductManager.getSupportedLocales()
                                               .stream()
                                               .skip(ThreadLocalRandom.current().nextInt(4))
                                               .findFirst()
                                               .get();
            StringBuilder log = new StringBuilder();
            log.append(clientId + " " + threadName + "\n-\tstart of log\t-\n");
            
            log.append(pm.getDiscounts(languageTag)
                             .entrySet() 
                             .stream()
                             .map(entry -> entry.getKey() + "\t" + entry.getValue())
                             .collect(Collectors.joining("\n")));
            
            Product product = pm.reviewProduct(productId, FOUR_STAR, "Yet another review!");
            log.append((product != null) ? "\nProduct " + productId + " reviewed\n" : "\nProduct " + productId + " not reviewed\n");
            
            pm.printProductReport(productId, languageTag, clientId);
            log.append(clientId + " generated report for " + productId + " product");
            
            log.append("\n-\tend of log\t-\n");
            return log.toString();
        };
        
        List<Callable<String>> clients = Stream.generate(() -> client)
                                               .limit(5)
                                               .collect(Collectors.toList());
        
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        try {
            List<Future<String>> results = executorService.invokeAll(clients);
            executorService.shutdown();
            results.stream().forEach(result -> {
                try {
                    System.out.println(result.get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error retrieving client log", ex);
                }    
            });
        }    
        catch (InterruptedException ex) {
            
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error invoking clients", ex);
        }
    
        
//        pm.printProductReport(101);
//        pm.printProductReport(105);
        
//        pm.dumpData();
//        pm.restoreData();
        
//        pm.printProductReport(101);
        
        
//        pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
//        pm.parseProduct("D, 101, Tea, 1.99, 0, 2019-09-19");
//        pm.printProductReport(101);
//        pm.printProductReport(42);
        
//        pm.reviewProduct(42, FOUR_STAR, "Nice hot cup of tea!");
//        pm.reviewProduct(101, FOUR_STAR, "Nice hot cup of tea!");
//        pm.parseReview("101, 4, Nice hot cup of tea");
        
//        pm.parseReview("1014, Nice hot cup of tea");
//        pm.parseReview("101, X, Nice hot cup of tea");
        
//        pm.reviewProduct(101, TWO_STAR, "Rather weak tea!");
//        pm.parseReview("101, 2, Rather weak tea");
//        pm.reviewProduct(101, FOUR_STAR, "Fine tea!");
//        pm.parseReview("101, 4, Fine tea!");
//        pm.reviewProduct(101, FOUR_STAR, "Good tea!");
//        pm.parseReview("101, 4, Good tea!");
//        pm.reviewProduct(101, FIVE_STAR, "Perfect tea!");
//        pm.parseReview("101, 5, Perfect tea!");
//        pm.reviewProduct(101, THREE_STAR, "Just add some lemon");
//        pm.parseReview("101, 3, Just add some lemon"  );
//        pm.printProductReport(101);
        
//        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), NOT_RATED);
//        pm.reviewProduct(102, THREE_STAR, "Coffe was ok");
//        pm.reviewProduct(102, ONE_STAR, "Where is the milk");
//        pm.reviewProduct(102, FIVE_STAR, "Perfect with ten spoons of sugar!");
//        pm.changeLocale("ru-RU");
//        pm.printProductReport(102);
        
//        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), NOT_RATED, LocalDate.now().plusDays(2));
//        pm.reviewProduct(103, FIVE_STAR, "Very nice cake");
//        pm.reviewProduct(103, FOUR_STAR, "Expected more chocolate");
//        pm.reviewProduct(103, FIVE_STAR, "Perfect cake!");
//        pm.changeLocale("en-GB");
//        pm.printProductReport(103);

//        pm.createProduct(105, "Cookie", BigDecimal.valueOf(3.99), NOT_RATED, LocalDate.now());
//        pm.reviewProduct(105, THREE_STAR, "Just another cookie");
//        pm.reviewProduct(105, THREE_STAR, "ok!");
//        pm.changeLocale("ru-RU");
//        pm.printProductReport(105);
        
//        pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));
//        
//       
//        pm.printProducts(p->p.getPrice().floatValue() < 2, 
//                        (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
//        
//        pm.printProducts((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));
//        
//        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
//        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
//        
//        pm.printProducts(ratingSorter.thenComparing(priceSorter));
//        pm.printProducts(priceSorter.thenComparing(ratingSorter));
        
//        Product p5 = p3.applyRating(THREE_STAR);
//        
//        Product p6 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p7 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), FIVE_STAR);
//        System.out.println(p6.equals(p7));
//        
//        Product p8 = p4.applyRating(FIVE_STAR);
//        Product p9 = p1.applyRating(TWO_STAR);
//        
////        p1.setId(101);
////        p1.setName("Tea");
////        p1.setPrice(BigDecimal.valueOf(1.99));
//        
////        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getRating().getStars());
////        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getRating().getStars());
////        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getRating().getStars());
////        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getRating().getStars());
////        System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getRating().getStars());
//
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
//        System.out.println(p5);
//        System.out.println(p8);
//        System.out.println(p9);
//        
////        if (p3 instanceof Food) {
////            System.out.println(((Food) p3).getBestBefore());
////        }
//
//        System.out.println(p3.getBestBefore());
//        System.out.println(p1.getBestBefore());

    }
    
}
