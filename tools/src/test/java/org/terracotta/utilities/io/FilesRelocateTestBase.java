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
package org.terracotta.utilities.io;

import org.junit.Before;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Path;

import static java.nio.file.Files.createDirectory;
import static org.junit.Assume.assumeTrue;

/**
 * Common core for {@link Files#relocate(Path, Path, CopyOption...)} testing.
 */
public abstract class FilesRelocateTestBase extends FilesTestBase {

  protected Path target;

  @Before
  public void prepareTarget() throws IOException {
    target = createDirectory(root.resolve("target_" + testName.getMethodName()));  // root/target
  }

  protected static Path makeFile(Path path, Iterable<String> lines) throws IOException {
    java.nio.file.Files.createFile(path);
    return java.nio.file.Files.write(path, lines, StandardCharsets.UTF_8);
  }

  protected static Path makeDirectory(Path path) throws IOException {
    return java.nio.file.Files.createDirectory(path);
  }

  protected static Path makeFileSymlink(Path filePath, Iterable<String> lines) throws IOException {
    assumeTrue("Skipped because symbolic links cannot be created in current environment", symlinksSupported);
    Path linkTarget = TEST_ROOT.newFile().toPath();
    java.nio.file.Files.write(linkTarget, lines, StandardCharsets.UTF_8);
    return java.nio.file.Files.createSymbolicLink(filePath, linkTarget);
  }

  protected static Path makeDirectorySymlink(Path dirPath) throws IOException {
    assumeTrue("Skipped because symbolic links cannot be created in current environment", symlinksSupported);
    return java.nio.file.Files.createSymbolicLink(dirPath, TEST_ROOT.newFolder().toPath());
  }
}
