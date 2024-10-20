/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.HashMap;

/**
 *
 * @author se180663
 */
public interface IRAMrepository<K,V> {
    int create (V  newItem);
    HashMap <K,V> read();
    V details (K code);
    int update(V edititem);
    int delete(K code );
}
