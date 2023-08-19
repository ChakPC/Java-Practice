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
package labs.pm.data;

import java.io.Serializable;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;

/**
 * (@code Product) This class handles all operations for a product
 * <br>
 * Each product has id, name and price
 * <br>
 * Each product can have a discount calculated based on a
 * (@link DISCOUNT_RATE discount rate)
 * @version 1.0
 * @author Purnadip Chakrabarti
 */
public abstract class Product implements Rateable<Product>, Serializable{
    private final int id;
    private final String name;
    private final BigDecimal price;
    private Rating rating;

    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

//    Product(int id, String name, BigDecimal price) {
//        this(id, name, price, NOT_RATED);
//    }
//    
//    Product() {
//        this(0, "No Name", BigDecimal.valueOf(0));
//    }
    
    /**
     * A constant that defines a 
     * (@link java.math.BigDecimal BigDecimal) value of the discount rate
     * <br>
     * Discount rate is 10%
     */
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    public int getId() {
        return id;
    }

//    public void setId(final int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(final String name) {
//        this.name = name;
//    }
//
    public BigDecimal getPrice() {
        return price;
    }
//
//    public void setPrice(final BigDecimal price) {
//        this.price = price;
//    }
    
    /**
     * Calculates discount based on a product price and
     * (@link DISCOUNT_RATE discount rate)
     * @return a (@link java.math.BigDecimal BigDecimal)
     * value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    @Override
    public Rating getRating() {
        return rating;
    }
    
//    public abstract Product applyRating(Rating newRating);
    
    /**
     * Assumes that the best before date is today
     *
     * @return the value of bestBefore
     */
    public LocalDate getBestBefore() {
        return LocalDate.now();
    }
    
    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + getDiscount() + ", " + rating.getStars() + ", " + getBestBefore();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        final Product other = (Product) obj;
        return (id==other.id);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + id;
        return hash;
    }
}
