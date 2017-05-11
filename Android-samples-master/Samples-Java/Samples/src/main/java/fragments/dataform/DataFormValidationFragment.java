package fragments.dataform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telerik.android.sdk.R;
import com.telerik.widget.dataform.engine.NonEmptyValidator;
import com.telerik.widget.dataform.visualization.RadDataForm;

import activities.ExampleFragment;

public class DataFormValidationFragment extends Fragment implements ExampleFragment {
    @Override
    public String title() {
        return "Validation";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup layoutRoot = (ViewGroup) inflater.inflate(R.layout.fragment_dataform_validation, null);

        RadDataForm dataForm = new RadDataForm(this.getActivity());
        dataForm.setEntity(new Person());

        dataForm.getExistingEditorForProperty("Name").property().setValidator(new NonEmptyValidator());

        layoutRoot.addView(dataForm);

        return layoutRoot;
    }
}
