-- curr_timestamp: redis current timestamp(Unit of second)
redis.replicate_commands()
local curr_timestamp = tonumber(redis.call('TIME')[1])
local result = 1
redis.call("HMSET", KEYS[1],
    "last_second", curr_timestamp,
    "curr_permits", ARGV[1],
    "max_burst", ARGV[2])
return result
