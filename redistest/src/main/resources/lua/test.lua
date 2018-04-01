local times = redis.call('INCR', KEYS[1])
if times == 1 then
    redis.call('EXPIRE',KEYS[1], tonumber(ARGV[2]))
end
--return times

if times > tonumber(ARGV[1]) then
    return 0
end
return 1


--local current = redis.call('GET', KEYS[1])
--if current == ARGV[1] then
--    redis.call('SET', KEYS[1], ARGV[2])
--    return true
--end
--return false