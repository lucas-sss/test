if redis.call("exists", KEYS[1]) == 0 then
    redis.call("setex", KEYS[1], unpack(ARGV))
    local current = redis.call('GET', KEYS[1])

    return redis.call("setex", KEYS[1], unpack(ARGV))
end

return nil
