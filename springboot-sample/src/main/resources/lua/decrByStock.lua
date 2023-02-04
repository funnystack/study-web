local stockId = KEYS[1];
local decrNum = ARGV[1];
local userId = ARGV[2];
local result;
print('key为', stockId);
print('value为', decrNum);
local crtStock = redis.call('get', stockId);
print('当前库存为 :', crtStock);
if crtStock == false or crtStock < decrNum then
    result = -2
else
    result = redis.call('decrBy', stockId, decrNum)
    if result < 0 then
        result = -1
    else
        redis.call('set','win_'..stockId,userId)
        result = 0
    end
end
return result;