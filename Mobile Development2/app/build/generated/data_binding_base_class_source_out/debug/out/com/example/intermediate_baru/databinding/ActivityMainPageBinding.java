// Generated by view binder compiler. Do not edit!
package com.example.intermediate_baru.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.intermediate_baru.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainPageBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final BottomAppBar BottomBar;

  @NonNull
  public final BottomNavigationView bottomNav;

  @NonNull
  public final FloatingActionButton camera;

  @NonNull
  public final CoordinatorLayout container;

  @NonNull
  public final FrameLayout frameLayout;

  private ActivityMainPageBinding(@NonNull CoordinatorLayout rootView,
      @NonNull BottomAppBar BottomBar, @NonNull BottomNavigationView bottomNav,
      @NonNull FloatingActionButton camera, @NonNull CoordinatorLayout container,
      @NonNull FrameLayout frameLayout) {
    this.rootView = rootView;
    this.BottomBar = BottomBar;
    this.bottomNav = bottomNav;
    this.camera = camera;
    this.container = container;
    this.frameLayout = frameLayout;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.BottomBar;
      BottomAppBar BottomBar = ViewBindings.findChildViewById(rootView, id);
      if (BottomBar == null) {
        break missingId;
      }

      id = R.id.bottomNav;
      BottomNavigationView bottomNav = ViewBindings.findChildViewById(rootView, id);
      if (bottomNav == null) {
        break missingId;
      }

      id = R.id.camera;
      FloatingActionButton camera = ViewBindings.findChildViewById(rootView, id);
      if (camera == null) {
        break missingId;
      }

      CoordinatorLayout container = (CoordinatorLayout) rootView;

      id = R.id.frame_layout;
      FrameLayout frameLayout = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout == null) {
        break missingId;
      }

      return new ActivityMainPageBinding((CoordinatorLayout) rootView, BottomBar, bottomNav, camera,
          container, frameLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
