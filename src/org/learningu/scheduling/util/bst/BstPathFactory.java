/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.learningu.scheduling.util.bst;

import com.google.common.annotations.GwtCompatible;

/**
 * A factory for extending paths in a binary search tree.
 *
 * @author Louis Wasserman
 * @param <N> The type of binary search tree nodes used in the paths generated by this {@code
 *        BstPathFactory}.
 * @param <P> The type of paths constructed by this {@code BstPathFactory}.
 */
@GwtCompatible
interface BstPathFactory<N extends BstNode<?, N>, P extends BstPath<N, P>> {
  /**
   * Returns this path extended by one node to the specified {@code side}.
   */
  P extension(P path, BstSide side);

  /**
   * Returns the trivial path that starts at {@code root} and goes no further.
   */
  P initialPath(N root);
}
