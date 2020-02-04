package cyclops;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Anagrams{
  String URL_STR = "http://wiki.puzzlers.org/pub/wordlists/unixdict.txt";
  static< AUTOCLOSEABLE extends AutoCloseable, OUTPUT> Supplier<OUTPUT>
  tryWithResources(
      Callable<AUTOCLOSEABLE> callable, Function<AUTOCLOSEABLE, Supplier<OUTPUT>> function, Supplier<OUTPUT> defaultSupplier){
    return () -> {
      try( AUTOCLOSEABLE autoCloseable = callable.call()){
        return function.apply(autoCloseable).get();
      }catch (Throwable throwable){
        return defaultSupplier.get();
      }
    };
  }
  static <INPUT, OUTPUT> Function<INPUT, OUTPUT> function(Supplier<OUTPUT> supplier){
    return i -> supplier.get();
  }
  static void main(String... args){
    //ConcurrentSkipListMap 线程安全有序表 TreeMap
    Map<String, Collection<String>> anagrams = new ConcurrentSkipListMap<>();
    int count = tryWithResources(
        () -> new BufferedReader(
            new InputStreamReader(
                new URL(URL_STR).openStream()
            )
        ),
        reader -> () -> reader.lines()
            .parallel()
            .mapToInt(word ->{
              char[] chars = word.toCharArray();
              Arrays.parallelSort(chars);
              String key = new String(chars);
              Collection<String>  collection = anagrams.computeIfAbsent(
                  key, function(ArrayList::new)
              );
              collection.add(word);
              return collection.size();
            })
            .max()
            .orElse(0),
        () -> 0
    ).get();
    anagrams.values().stream()
        .filter(ans -> ans.size() >= count)
        .forEach(System.out::println);
  }
}