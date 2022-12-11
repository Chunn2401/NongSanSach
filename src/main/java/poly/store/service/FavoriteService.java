package poly.store.service;

import java.util.List;

import poly.store.entity.Favorite;

public interface FavoriteService {

	Favorite create(int id);

	List<Favorite> getListFavoriteByEmail();

	void delete(int id);

	Favorite getOneFavorite(int id);

}
