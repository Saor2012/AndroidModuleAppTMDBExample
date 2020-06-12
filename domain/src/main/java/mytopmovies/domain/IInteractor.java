package mytopmovies.domain;

import com.example.mytopmovies.data.BaseModel;

import io.reactivex.Observable;

public interface IInteractor {

    Observable<BaseModel> result(int page);

}
