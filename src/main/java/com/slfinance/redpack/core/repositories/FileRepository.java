package com.slfinance.redpack.core.repositories;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.slfinance.redpack.core.entities.File;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

public interface FileRepository extends BaseRepository<File>{
	
	@QueryExtend
	@Query(nativeQuery=true,value="select f.id,f.path,f.file_type from rp_t_file f ,rp_t_file_relation r where f.id = r.file_id and r.relate_table = :relateTable and r.relate_primary = :relatePrimary ")
	public List<Map<String, Object>> findByRelateTableAndRelatePrimary(@Param("relateTable")String relateTable,@Param("relatePrimary")String relatePrimary);
	
	/**
	 * 和上面方法一样，只是，path改成了pathURL
	 * 
	 * @author SangLy
	 * @createTime 2016年11月11日 下午1:11:57
	 * @param relateTable
	 * @param relatePrimary
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery=true,value="select f.id,f.path \"pathURL\",f.file_type from rp_t_file f ,rp_t_file_relation r where f.id = r.file_id and r.relate_table = :relateTable and r.relate_primary = :relatePrimary ")
	public List<Map<String, Object>> findByRelateTableAndRelatePrimaryPathURL(@Param("relateTable")String relateTable,@Param("relatePrimary")String relatePrimary);
}
