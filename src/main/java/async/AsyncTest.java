package async;

import io.vavr.collection.List;
import io.vavr.*;
import io.vavr.control.Option;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * You should complete the function in this class
 */
class AsyncTest {

  private static List<Enterprise> enterprises = List.of(
      new Enterprise("ent_1", "Google", "ceo_2"),
      new Enterprise("ent_2", "Facebook", "ceo_1")
  );

  private static List<Ceo> ceos = List.of(
      new Ceo("ceo_1", "Mark"),
      new Ceo("ceo_2", "Sundar"),
      new Ceo("ceo_3", "Bill")
  );

  public static CompletableFuture<Option<Ceo>> getCeoById(String ceo_id) {

    CompletableFuture<Option<Ceo>> completableFuture = new CompletableFuture<>();
    Executors.newCachedThreadPool().submit(() -> {
      Iterator<Ceo> iterator = ceos.iterator();
      while (iterator.hasNext()) {
        Ceo ceo = iterator.next();
        if(ceo.id.equals(ceo_id)) {
          completableFuture.complete(Option.of(ceo));
        }
      }
      completableFuture.complete(Option.none());
    });
    return completableFuture;
  }

  public static CompletableFuture<Option<Enterprise>> getEnterpriseByCeoId(String ceo_id) {
    CompletableFuture<Option<Enterprise>> completableFuture = new CompletableFuture<>();
    Executors.newCachedThreadPool().submit(() -> {
      Iterator<Enterprise> iterator = enterprises.iterator();
      while (iterator.hasNext()) {
        Enterprise enterprise = iterator.next();
        if(enterprise.ceo_id.equals(ceo_id)) {
          completableFuture.complete(Option.of(enterprise));
        }
      }
      completableFuture.complete(Option.none());
    });
    return completableFuture;
  }

  public static CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> getCEOAndEnterprise(String ceo_id) {
    CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> completableFuture = new CompletableFuture<>();
    Executors.newCachedThreadPool().submit(() -> {
      Future<Option<Ceo>> ceo = getCeoById(ceo_id);
      Future<Option<Enterprise>> enterprise = getEnterpriseByCeoId(ceo_id);
      Option<Ceo> ceo_final = Option.none();
      Option<Enterprise> enterprises_final = Option.none();
      try {
        ceo_final = ceo.get();
        enterprises_final = enterprise.get();
      }
      catch (InterruptedException | ExecutionException e)
      {
        System.out.println("Unable to complete async job");
      }
      Tuple2<Option<Ceo>, Option<Enterprise>> tuple2 = new Tuple2<>(ceo_final, enterprises_final);
      completableFuture.complete(tuple2);
    });
    return completableFuture;
  }

}
