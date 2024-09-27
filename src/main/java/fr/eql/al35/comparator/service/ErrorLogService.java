package fr.eql.al35.comparator.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eql.al35.comparator.entity.ErrorLog;
import fr.eql.al35.comparator.repository.ErrorLogIRepository;

@Service
public class ErrorLogService implements ErrorLogIRepository {

	@Override
	public <S extends ErrorLog> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ErrorLog> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ErrorLog> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ErrorLog> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ErrorLog> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ErrorLog entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends ErrorLog> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
