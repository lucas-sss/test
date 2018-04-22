
if redis.call("exists", KEYS[1]) == 0 then
    return redis.call("setex", KEYS[1], ARGV[1])
end
