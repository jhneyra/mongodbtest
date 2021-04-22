package mongodbtest;

import mongodbtest.entity.LogQuery;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AnalyticsMap extends HashMap<Long, HashMap<String, LogQuery>>
{
}
