package bugFixing;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ROSH - 2/7/2020
 */
public class Reconciler {

  static Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {

    if (pending == null || processed == null) {
      return Stream.empty();
    }

    Set<Long> filteredProcessedId = processed
        .flatMap(Function.identity())
        .filter(Objects::nonNull)
        .filter(p -> Objects.nonNull(p.getId()))
        .filter(p -> Objects.nonNull(p.getStatus()))
        .filter(p -> p.getStatus().isPresent() && "DONE".equalsIgnoreCase(p.getStatus().get()))
        .filter(p -> !p.getId().trim().isEmpty())
        .map(p -> Long.parseLong(p.getId())).collect(Collectors.toSet());
    return pending.filter(p -> filteredProcessedId.contains(p.getId()));

  }


  //burdan asagisina gerek yoxdu codility'de
  static class PendingTransaction {

    Long id;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return "PendingTransaction{" +
          "id=" + id +
          '}';
    }
  }

  static class ProcessedTransaction {

    Optional<String> status;
    String id;

    public Optional<String> getStatus() {
      return status;
    }

    public void setStatus(Optional<String> status) {
      this.status = status;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }

}