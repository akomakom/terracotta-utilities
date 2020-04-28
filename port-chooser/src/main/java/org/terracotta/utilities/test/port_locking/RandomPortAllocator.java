/*
 * Copyright 2020 Terracotta, Inc., a Software AG company.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terracotta.utilities.test.port_locking;

import java.util.Random;

public class RandomPortAllocator implements PortAllocator {
  private static final int LOWEST_PORT_INCLUSIVE = 1024;
  private static final int HIGHEST_PORT_INCLUSIVE = 32767;

  private final Random random;

  public RandomPortAllocator(Random random) {
    this.random = random;
  }

  @Override
  public int allocatePorts(int portCount) {
    int highestPortAdjustment = portCount - 1;
    return getRandomIntInRange(LOWEST_PORT_INCLUSIVE, HIGHEST_PORT_INCLUSIVE - highestPortAdjustment);
  }

  private int getRandomIntInRange(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }
}
