# Write your MySQL query statement below
select 
    a.product_id, 
    ifnull(round(sum(units*price)/sum(units), 2), 0) as average_price
from Prices a
left join UnitsSold b
on a.product_id = b.product_id
and b.purchase_date between start_date and end_date
group by a.product_id;

-- runtime - 613ms
# Write your MySQL query statement below
SELECT
a.product_id
,round(coalesce((SUM(a.price*b.units)/sum(b.units)),0),2) AS average_price
FROM Prices a
LEFT JOIN UnitsSold b
ON a.product_id = b.product_id
AND b.purchase_date >= a.start_date AND b.purchase_date <= a.end_date
GROUP BY 1

-- runtime - 621ms
# Write your MySQL query statement below
select p.product_id, COALESCE(round(sum(units*price)/ sum(units),2),0) as average_price from Prices p
left join UnitsSold u 
on p.product_id = u.product_id
and u.purchase_date BETWEEN p.start_date and p.end_date
group by product_id;

-- runtime - 629ms
# Write your MySQL query statement below
SELECT p.product_id, IFNULL(ROUND(SUM(p.price * u.units) / SUM(u.units), 2), 0) AS average_price
FROM Prices p
LEFT JOIN UnitsSold u
    ON p.product_id = u.product_id
    AND u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;

-- runtime - 637ms
SELECT  p.product_id, ROUND(COALESCE(SUM(u.units * p.price) /COALESCE(SUM(u.units),0),0),2)
    AS average_price
FROM Prices p LEFT JOIN UnitsSold u ON p.product_id = u.product_id 
    AND u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;
