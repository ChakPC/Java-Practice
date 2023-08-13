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
import labs.pm.data.Product;
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
        
        Product p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
        Product p2 = new Product(102, "Coffee", BigDecimal.valueOf(1.99), FOUR_STAR);
        Product p3 = new Product(103, "Cake", BigDecimal.valueOf(3.99), FIVE_STAR);
        Product p4 = new Product();
        Product p5 = p3.applyRating(THREE_STAR);
        
//        p1.setId(101);
//        p1.setName("Tea");
//        p1.setPrice(BigDecimal.valueOf(1.99));
        
        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getRating().getStars());
        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getRating().getStars());
        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getRating().getStars());
        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getRating().getStars());
        System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getRating().getStars());
    }
    
}
