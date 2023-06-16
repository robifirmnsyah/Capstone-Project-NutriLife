// Generated by view binder compiler. Do not edit!
package com.example.intermediate_baru.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.intermediate_baru.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button ButtonRegister;

  @NonNull
  public final TextInputEditText edEmail;

  @NonNull
  public final TextInputEditText edName;

  @NonNull
  public final TextInputEditText edPassword;

  @NonNull
  public final TextView text;

  @NonNull
  public final TextView textLogin;

  @NonNull
  public final TextInputLayout tvEmail;

  @NonNull
  public final TextInputLayout tvName;

  @NonNull
  public final TextInputLayout tvPassword;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button ButtonRegister, @NonNull TextInputEditText edEmail,
      @NonNull TextInputEditText edName, @NonNull TextInputEditText edPassword,
      @NonNull TextView text, @NonNull TextView textLogin, @NonNull TextInputLayout tvEmail,
      @NonNull TextInputLayout tvName, @NonNull TextInputLayout tvPassword) {
    this.rootView = rootView;
    this.ButtonRegister = ButtonRegister;
    this.edEmail = edEmail;
    this.edName = edName;
    this.edPassword = edPassword;
    this.text = text;
    this.textLogin = textLogin;
    this.tvEmail = tvEmail;
    this.tvName = tvName;
    this.tvPassword = tvPassword;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ButtonRegister;
      Button ButtonRegister = ViewBindings.findChildViewById(rootView, id);
      if (ButtonRegister == null) {
        break missingId;
      }

      id = R.id.ed_Email;
      TextInputEditText edEmail = ViewBindings.findChildViewById(rootView, id);
      if (edEmail == null) {
        break missingId;
      }

      id = R.id.ed_Name;
      TextInputEditText edName = ViewBindings.findChildViewById(rootView, id);
      if (edName == null) {
        break missingId;
      }

      id = R.id.ed_Password;
      TextInputEditText edPassword = ViewBindings.findChildViewById(rootView, id);
      if (edPassword == null) {
        break missingId;
      }

      id = R.id.text;
      TextView text = ViewBindings.findChildViewById(rootView, id);
      if (text == null) {
        break missingId;
      }

      id = R.id.textLogin;
      TextView textLogin = ViewBindings.findChildViewById(rootView, id);
      if (textLogin == null) {
        break missingId;
      }

      id = R.id.tv_Email;
      TextInputLayout tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_Name;
      TextInputLayout tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_Password;
      TextInputLayout tvPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvPassword == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, ButtonRegister, edEmail,
          edName, edPassword, text, textLogin, tvEmail, tvName, tvPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
