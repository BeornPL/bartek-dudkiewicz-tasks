package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("test_id", "test_name", true));
        //When
        List<TrelloList> mappedTrelloList = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(1, mappedTrelloList.size());
        assertEquals("test_id",mappedTrelloList.get(0).getId());
        assertEquals("test_name", mappedTrelloList.get(0).getName());
        assertEquals(true, mappedTrelloList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("test_id", "test_name", true));
        //When
        List<TrelloListDto> mappedTrelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(1, mappedTrelloListDto.size());
        assertEquals("test_id",mappedTrelloListDto.get(0).getId());
        assertEquals("test_name", mappedTrelloListDto.get(0).getName());
        assertEquals(true, mappedTrelloListDto.get(0).isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("test_id", "test_name", new ArrayList<>()));
        //When
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(1, mappedTrelloBoard.size());
        assertEquals("test_id",mappedTrelloBoard.get(0).getId());
        assertEquals("test_name", mappedTrelloBoard.get(0).getName());
        assertEquals(new ArrayList<>(), mappedTrelloBoard.get(0).getLists());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("test_id", "test_name", new ArrayList<>()));
        //When
        List<TrelloBoardDto> mappedTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertEquals(1, mappedTrelloBoardDto.size());
        assertEquals("test_id",mappedTrelloBoardDto.get(0).getId());
        assertEquals("test_name", mappedTrelloBoardDto.get(0).getName());
        assertEquals(new ArrayList<>(), mappedTrelloBoardDto.get(0).getLists());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test_desc", "test_pos", "test_listId");
        //When
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("test_name",mappedTrelloCard.getName());
        assertEquals("test_desc",mappedTrelloCard.getDescription());
        assertEquals("test_pos",mappedTrelloCard.getPos());
        assertEquals("test_listId",mappedTrelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_name", "test_desc", "test_pos", "test_listId");
        //When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("test_name",mappedTrelloCardDto.getName());
        assertEquals("test_desc",mappedTrelloCardDto.getDescription());
        assertEquals("test_pos",mappedTrelloCardDto.getPos());
        assertEquals("test_listId",mappedTrelloCardDto.getListId());
    }
}
