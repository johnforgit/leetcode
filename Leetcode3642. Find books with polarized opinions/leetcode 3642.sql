select book_id, title ,author,genre ,pages,
max(session_rating)-min(session_rating) rating_spread ,
round(sum(case when session_rating>3 or session_rating <3 then 1 else 0 end )*1.0/count(*),2) polarization_score 
from books
join reading_sessions 
using(book_id)
group by 1,2,3,4,5
having count(*)>4
and sum(case when session_rating>3 then 1 else 0 end)>0
and sum(case when session_rating<3 then 1 else 0 end)>0
and 
polarization_score>=0.6
order by polarization_score desc,title desc

-- runtime - 436ms
# Write your MySQL query statement below
SELECT
    b.book_id,
    b.title,
    b.author,
    b.genre,
    b.pages,
    MAX(r.session_rating) - MIN(r.session_rating) AS rating_spread,
    ROUND(SUM(CASE WHEN r.session_rating <= 2 OR r.session_rating >= 4 THEN 1 ELSE 0 END) / COUNT(*), 2) AS polarization_score
FROM
    books b
JOIN 
    reading_sessions r
    ON b.book_id = r.book_id
GROUP BY 
    b.book_id
HAVING
    ROUND(SUM(CASE WHEN r.session_rating <= 2 OR r.session_rating >= 4 THEN 1 ELSE 0 END) / COUNT(*), 2) >= 0.6
    AND
    COUNT(*) >= 5
    AND
    SUM(CASE WHEN r.session_rating >= 4 THEN 1 ELSE 0 END) >= 1
    AND 
    SUM(CASE WHEN r.session_rating <= 2 THEN 1 ELSE 0 END) >= 1
    AND 
    COUNT(DISTINCT CASE 
        WHEN r.session_rating <= 2 OR r.session_rating >= 4 
        THEN r.reader_name 
        END) >= 2
ORDER BY
    polarization_score DESC,
    b.title DESC

-- runtime - 442ms
# Write your MySQL query statement below
select
    b.book_id, b.title, b.author, b.genre, b.pages,
    max(r.session_rating) - min(r.session_rating) as rating_spread,
    round(
        sum(case when r.session_rating<=2 or r.session_rating>=4 then 1 else 0 end)/count(r.session_id), 2) as polarization_score
from
    books b join reading_sessions r on b.book_id = r.book_id
group by
    b.book_id, b.title, b.author, b.genre, b.pages
having
    max(r.session_rating)>=4 and min(r.session_rating)<=2
    and count(r.session_id)>=5
order by
    polarization_score desc, title desc

-- runtime - 448ms
# Write your MySQL query statement below
select b.book_id,b.title,b.author,b.genre,b.pages,max(rs.session_rating)-min(rs.session_rating) as rating_spread,round(sum(case when rs.session_rating>=4
 or rs.session_rating<=2 then 1 else 0 end)*1.0/count(*),2) as polarization_score
 from books as b
join reading_sessions as rs
on b.book_id=rs.book_id
group by b.book_id,b.title,b.author,b.genre,b.pages
having count(*)>=5
and max(rs.session_rating)>=4
and min(rs.session_rating)<=2
and (sum(case when rs.session_rating<=2 or rs.session_rating>=4 then 1 else 0 end)*1.0/count(*))>=0.6
order by polarization_score desc,b.title desc
