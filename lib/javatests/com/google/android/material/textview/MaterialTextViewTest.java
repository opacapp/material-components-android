/*
 * Copyright (C) 2019 The Android Open Source Project
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

package com.google.android.material.textview;

import com.google.android.material.R;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;

/** Tests for {@link MaterialTextView} */
@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class MaterialTextViewTest {

  private final Context context = ApplicationProvider.getApplicationContext();
  private final LayoutInflater inflater = LayoutInflater.from(context);
  private int testLineHeight;
  private int testLineHeightOverride;

  @Before
  public void readTestLineHeights() {
    Resources resources = context.getResources();
    testLineHeight = resources.getDimensionPixelSize(R.dimen.material_text_view_test_line_height);
    testLineHeightOverride =
        resources.getDimensionPixelSize(R.dimen.material_text_view_test_line_height_override);
  }

  @Test
  public void ensureThatCreatedViewUsesLineHeightFromTextAppearance() {
    MaterialTextView textView =
        (MaterialTextView)
            inflater.inflate(R.layout.text_view_with_line_height_from_appearance, null, false);
    final int actualLineHeight = textView.getLineHeight();
    assertThat(actualLineHeight).isEqualTo(testLineHeight);
  }

  @Test
  public void ensureThatCreatedViewUsesLineHeightFromStyleWithTextAppearance() {
    MaterialTextView textView =
        (MaterialTextView)
            inflater.inflate(R.layout.text_view_with_line_height_from_style, null, false);
    final int actualLineHeight = textView.getLineHeight();
    assertThat(actualLineHeight).isEqualTo(testLineHeight);
  }

  @Test
  public void ensureThatLineHeightFromLayoutOverridesThatFromTextAppearance() {
    MaterialTextView textView =
        (MaterialTextView)
            inflater.inflate(R.layout.text_view_with_line_height_from_layout, null, false);
    final int actualLineHeight = textView.getLineHeight();
    assertThat(actualLineHeight).isEqualTo(testLineHeightOverride);
  }

  @Test
  public void ensureThatViewAppliesLineHeightWhenSettingTextAppearance() {
    MaterialTextView textView = new MaterialTextView(context);
    textView.setTextAppearance(context, R.style.TestStyleWithLineHeight);
    final int actualLineHeight = textView.getLineHeight();
    assertThat(actualLineHeight).isEqualTo(testLineHeight);
  }
}
