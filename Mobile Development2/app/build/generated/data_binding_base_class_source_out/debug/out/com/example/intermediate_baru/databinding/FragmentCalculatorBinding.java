// Generated by view binder compiler. Do not edit!
package com.example.intermediate_baru.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.intermediate_baru.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCalculatorBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonresult;

  @NonNull
  public final EditText inputgram;

  @NonNull
  public final EditText kaloribahan;

  @NonNull
  public final MaterialCardView materialCardView;

  @NonNull
  public final TextView resultTextView;

  @NonNull
  public final SearchView searchBar;

  @NonNull
  public final ListView searchresult;

  private FragmentCalculatorBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonresult, @NonNull EditText inputgram, @NonNull EditText kaloribahan,
      @NonNull MaterialCardView materialCardView, @NonNull TextView resultTextView,
      @NonNull SearchView searchBar, @NonNull ListView searchresult) {
    this.rootView = rootView;
    this.buttonresult = buttonresult;
    this.inputgram = inputgram;
    this.kaloribahan = kaloribahan;
    this.materialCardView = materialCardView;
    this.resultTextView = resultTextView;
    this.searchBar = searchBar;
    this.searchresult = searchresult;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCalculatorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCalculatorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_calculator, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCalculatorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonresult;
      Button buttonresult = ViewBindings.findChildViewById(rootView, id);
      if (buttonresult == null) {
        break missingId;
      }

      id = R.id.inputgram;
      EditText inputgram = ViewBindings.findChildViewById(rootView, id);
      if (inputgram == null) {
        break missingId;
      }

      id = R.id.kaloribahan;
      EditText kaloribahan = ViewBindings.findChildViewById(rootView, id);
      if (kaloribahan == null) {
        break missingId;
      }

      id = R.id.materialCardView;
      MaterialCardView materialCardView = ViewBindings.findChildViewById(rootView, id);
      if (materialCardView == null) {
        break missingId;
      }

      id = R.id.resultTextView;
      TextView resultTextView = ViewBindings.findChildViewById(rootView, id);
      if (resultTextView == null) {
        break missingId;
      }

      id = R.id.searchBar;
      SearchView searchBar = ViewBindings.findChildViewById(rootView, id);
      if (searchBar == null) {
        break missingId;
      }

      id = R.id.searchresult;
      ListView searchresult = ViewBindings.findChildViewById(rootView, id);
      if (searchresult == null) {
        break missingId;
      }

      return new FragmentCalculatorBinding((ConstraintLayout) rootView, buttonresult, inputgram,
          kaloribahan, materialCardView, resultTextView, searchBar, searchresult);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
