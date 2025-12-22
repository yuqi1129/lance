/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lance.index;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Description of an index containing its metadata and statistics.
 * This provides detailed information about an index including its type, distance metric,
 * and coverage statistics.
 */
public class IndexDescription {
  public static final String JSON_PROPERTY_DISTANCE_TYPE = "distance_type";
  @Nullable
  private String distanceType;

  public static final String JSON_PROPERTY_INDEX_TYPE = "index_type";
  @Nullable
  private String indexType;

  public static final String JSON_PROPERTY_NUM_INDEXED_ROWS = "num_indexed_rows";
  @Nullable
  private Long numIndexedRows;

  public static final String JSON_PROPERTY_NUM_UNINDEXED_ROWS = "num_unindexed_rows";
  @Nullable
  private Long numUnindexedRows;

  private IndexDescription(
      String distanceType,
      String indexType,
      Long numIndexedRows,
      Long numUnindexedRows) {
    this.distanceType = distanceType;
    this.indexType = indexType;
    this.numIndexedRows = numIndexedRows;
    this.numUnindexedRows = numUnindexedRows;
  }

  /**
   * Get the distance type used by the index (e.g., "l2", "cosine", "dot").
   * This is only applicable for vector indices.
   *
   * @return the distance type, or null if not applicable
   */
  @Nullable
  public String getDistanceType() {
    return distanceType;
  }

  /**
   * Get the type of the index (e.g., "IVF_PQ", "BTREE", "BITMAP", "HNSW").
   *
   * @return the index type
   */
  @Nullable
  public String getIndexType() {
    return indexType;
  }

  /**
   * Get the number of rows covered by this index.
   *
   * @return the number of indexed rows, or null if unavailable
   */
  @Nullable
  public Long getNumIndexedRows() {
    return numIndexedRows;
  }

  /**
   * Get the number of rows in the dataset not covered by this index.
   *
   * @return the number of unindexed rows, or null if unavailable
   */
  @Nullable
  public Long getNumUnindexedRows() {
    return numUnindexedRows;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndexDescription that = (IndexDescription) o;
    return Objects.equals(distanceType, that.distanceType)
        && Objects.equals(indexType, that.indexType)
        && Objects.equals(numIndexedRows, that.numIndexedRows)
        && Objects.equals(numUnindexedRows, that.numUnindexedRows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distanceType, indexType, numIndexedRows, numUnindexedRows);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("distanceType", distanceType)
        .add("indexType", indexType)
        .add("numIndexedRows", numIndexedRows)
        .add("numUnindexedRows", numUnindexedRows)
        .toString();
  }

  /**
   * Create a new builder for IndexDescription.
   *
   * @return a new builder
   */
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String distanceType;
    private String indexType;
    private Long numIndexedRows;
    private Long numUnindexedRows;

    private Builder() {}

    public Builder distanceType(String distanceType) {
      this.distanceType = distanceType;
      return this;
    }

    public Builder indexType(String indexType) {
      this.indexType = indexType;
      return this;
    }

    public Builder numIndexedRows(Long numIndexedRows) {
      this.numIndexedRows = numIndexedRows;
      return this;
    }

    public Builder numUnindexedRows(Long numUnindexedRows) {
      this.numUnindexedRows = numUnindexedRows;
      return this;
    }

    public IndexDescription build() {
      return new IndexDescription(distanceType, indexType, numIndexedRows, numUnindexedRows);
    }
  }
}
