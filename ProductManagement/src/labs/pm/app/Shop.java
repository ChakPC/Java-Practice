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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import labs.pm.data.Drink;
import labs.pm.data.Food;
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
        
        ProductManager pm = new ProductManager(Locale.UK);
        
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.printProductReport();
        
        p1 = pm.reviewProduct(p1, FOUR_STAR, "Nice hot cup of tea!");
        p1 = pm.reviewProduct(p1, TWO_STAR, "Rather weak tea!");
        p1 = pm.reviewProduct(p1, FOUR_STAR, "Fine tea!");
        p1 = pm.reviewProduct(p1, FOUR_STAR, "Good tea!");
        p1 = pm.reviewProduct(p1, FIVE_STAR, "Perfect tea!");
        p1 = pm.reviewProduct(p1, THREE_STAR, "Just add some lemon");
        pm.printProductReport();
        
//        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), FOUR_STAR);
//        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p4 = pm.createProduct(105, "Cookie", BigDecimal.valueOf(3.99), TWO_STAR, LocalDate.now());
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
