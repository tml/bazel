// Copyright 2014 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.rules.cpp;

import com.google.common.base.Preconditions;
import com.google.devtools.build.lib.actions.Artifact;
import com.google.devtools.build.lib.analysis.TransitiveInfoProvider;
import com.google.devtools.build.lib.concurrent.ThreadSafety.Immutable;

import javax.annotation.Nullable;

/**
 * Provides the binary artifact and its associated .dwp files, if fission is enabled.
 * If Fission ({@link https://gcc.gnu.org/wiki/DebugFission}) is not enabled, the
 * dwp file will be null.
 */
@Immutable
public final class CppDebugPackageProvider implements TransitiveInfoProvider {

  private final Artifact strippedArtifact;
  private final Artifact unstrippedArtifact;
  @Nullable private final Artifact dwpArtifact;

  public CppDebugPackageProvider(
      Artifact strippedArtifact,
      Artifact unstrippedArtifact,
      @Nullable Artifact dwpArtifact) {
    Preconditions.checkNotNull(strippedArtifact);
    Preconditions.checkNotNull(unstrippedArtifact);
    this.strippedArtifact = strippedArtifact;
    this.unstrippedArtifact = unstrippedArtifact;
    this.dwpArtifact = dwpArtifact;
  }

  /**
   * Returns the stripped file (the explicit ".stripped" target).
   */
  public final Artifact getStrippedArtifact() {
    return strippedArtifact;
  }

  /**
   * Returns the unstripped file (the default executable target).
   */
  public final Artifact getUnstrippedArtifact() {
    return unstrippedArtifact;
  }

  /**
   * Returns the .dwp file (for fission builds) or null if --fission=no.
   */
  @Nullable
  public final Artifact getDwpArtifact() {
    return dwpArtifact;
  }

}
