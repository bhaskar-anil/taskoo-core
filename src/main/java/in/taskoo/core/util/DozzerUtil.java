package in.taskoo.core.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DozzerUtil<S,T> {

	private final Mapper mapper;
	
	public List<T> mapList(List<S> source,Class<T> type) {
		List<T> results = new ArrayList<>();
		source
			.parallelStream()
			.forEachOrdered(s -> results.add(mapper.map(s, type)));
		return results;
	}
}
